package com.imdb.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imdb.demo.movie.Genre;
import com.imdb.demo.movie.GenreResult;
import com.imdb.demo.movie.MovieResult;
import com.imdb.demo.movie.MovieSearchResult;
import com.imdb.demo.utils.RequestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by Kinath on 6/11/2017.
 */
public class ImdbDemo
{
    public static String API_KEY = "";

    public static void main( String[] args )
    {
        loadConfigurations();

        HttpClient apacheHttpClient = HttpClientBuilder.create().build();
        ObjectMapper objectMapper = new ObjectMapper();

        HttpGet genreRequest = RequestUtils.genreSearchRequest(API_KEY);
        HttpResponse genreResponse = null;
        GenreResult genreResult = null;
        HashMap<Integer, String> genreMap = new HashMap<Integer, String>();

        try
        {
            genreResponse = apacheHttpClient.execute( genreRequest );
            genreResult = objectMapper.readValue( genreResponse.getEntity().getContent(), GenreResult.class );
            for( Genre genre : genreResult.getGenres() )
            {
                genreMap.put( genre.getId(), genre.getName() );
            }
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        HttpGet movieSearchRequest = RequestUtils.generateMovieSearchRequest( API_KEY, "2015", 1, false, null, -1, -1 );
        HttpResponse movieResponse = null;
        MovieSearchResult movieSearchResult = null;
        List<MovieResult> movieResultList = new ArrayList<MovieResult>();
        int page = 1;
        int totalPages = -1;

        try
        {
            movieResponse = apacheHttpClient.execute( movieSearchRequest);
            movieSearchResult = objectMapper.readValue(movieResponse.getEntity().getContent(), MovieSearchResult.class);
            totalPages = movieSearchResult.getTotalPages();
            movieResultList.addAll( movieSearchResult.getResults() );
            while( page < totalPages )
            {
                System.out.println("Processing Page : " + page);
                page++;
                movieSearchRequest = RequestUtils.generateMovieSearchRequest( API_KEY, "2015", page, false, null, -1, -1 );
                movieResponse = apacheHttpClient.execute( movieSearchRequest );
                movieSearchResult = objectMapper.readValue(movieResponse.getEntity().getContent(), MovieSearchResult.class);
                movieResultList.addAll( movieSearchResult.getResults() );
            }
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }

        System.out.println("DONE");

    }

    public static void loadConfigurations()
    {
        InputStream  inputStream = null;
        Properties prop = new Properties();

        try
        {
            inputStream = new FileInputStream( "src\\main\\resources\\apiconfig.properties" );
            prop.load( inputStream );

            System.out.println(prop.getProperty( "API_KEY" ));
            API_KEY = prop.getProperty( "API_KEY" );
        }
        catch( FileNotFoundException e )
        {
            e.printStackTrace();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                inputStream.close();
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
        }
    }
}
