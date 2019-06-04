package org.tutorial.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuoteDAO {

    private NamedParameterJdbcTemplate myJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setMyJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.myJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public Quote findById(Integer id){
        MapSqlParameterSource myMap = new MapSqlParameterSource();
        myMap.addValue("id",id);

        try {
            Quote quote = myJdbcTemplate.queryForObject("SELECT * FROM quotes WHERE id =:id", myMap, new RowMapper<Quote>() {
                public Quote mapRow(ResultSet rs, int rowNum)
                        throws SQLException {

                    Quote quote = new Quote();
                    quote.setId(rs.getInt("id"));
                    quote.setQuote(rs.getString("quote"));
                    quote.setAuthor(rs.getString("author"));
                    return quote;
                }
            });
            return quote;
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Quote> getQuotes(){

        return myJdbcTemplate.query("SELECT * FROM quotes", new RowMapper<Quote>() {
            public Quote mapRow(ResultSet rs, int rowNum)
                    throws SQLException {

                Quote quote = new Quote();
                quote.setId(rs.getInt("id"));
                quote.setQuote(rs.getString("quote"));
                quote.setAuthor(rs.getString("author"));
                return quote;
            }});
    }

    public Integer getCountOfQuotes(){
        //Ensure Integer is returned
        Integer value = jdbcTemplate.queryForObject("SELECT count(*) FROM quotes", Integer.class);

        return value;
    }

}
