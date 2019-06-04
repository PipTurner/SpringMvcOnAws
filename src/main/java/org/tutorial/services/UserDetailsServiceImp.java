package org.tutorial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.tutorial.model.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private NamedParameterJdbcTemplate myJdbcTemplate;

    @Autowired
    public void setMyJdbcTemplate(DataSource dataSource) {
        this.myJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(
                    "No user found with username: "+ username);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return  new org.springframework.security.core.userdetails.User
                (user.getUsername(),
                        user.getPassword().toLowerCase(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                        getAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    public User findByUsername(String userName){
        MapSqlParameterSource myMap = new MapSqlParameterSource();
        myMap.addValue("name",userName);

        try {
            User user = myJdbcTemplate.queryForObject("SELECT * FROM users WHERE username =:name", myMap, new RowMapper<User>() {
                public User mapRow(ResultSet rs, int rowNum)
                        throws SQLException {

                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEnabled(rs.getShort("enabled"));
                    return user;
                }
            });

            user.setRoles(findRolesByUserName(userName));

            return user;

        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String[] findRolesByUserName(String userName){

        MapSqlParameterSource myMap = new MapSqlParameterSource();
        myMap.addValue("name",userName);

        List<String> roles = myJdbcTemplate.query("SELECT authority FROM authorities WHERE username =:name",myMap, new RowMapper<String>() {
            public String mapRow(ResultSet rs, int rowNum)
                    throws SQLException {

                String role = rs.getString("authority");
                return role;
            }});

        return roles.toArray(new String[0]);
    }

    public void saveUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled((short)1);

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);

        int numOfRowsAffected = myJdbcTemplate.update("INSERT into users (username,password, enabled) values (:username, :password, :enabled)",params);

        if (numOfRowsAffected == 1) {
            System.out.println("One row added to table users successfully");

            saveRoles(user);
        }
        else{
            System.out.println("There was a problem adding to table users");
        }
    }

    public void saveRoles(User user){

        for (String role: user.getRoles()) {

            MapSqlParameterSource myMap = new MapSqlParameterSource();

            myMap.addValue("username",user.getUsername());
            myMap.addValue("authority",role);

            int numOfRowsAffected = myJdbcTemplate.update("INSERT into authorities (username,authority) values (:username, :authority)",myMap);

            if (numOfRowsAffected == 1) {
                System.out.println("One row added to table authorities successfully");
            }
            else{
                System.out.println("There was a problem adding to table authorities");
            }
        }
    }



}
