package com.imdb.demo.utils;

import org.apache.http.client.methods.HttpGet;

/**
 * Created by Kinath on 6/11/2017.
 */
public class RequestUtils
{
    public static HttpGet generateMovieSearchRequest( String apiKey, String query, int page, boolean includeAdult, String region, int year, int primaryReleaseYear )
    {
        //"https://api.themoviedb.org/3/search/movie?api_key=<<KEY>>&language=en-US&query=2015&page=1&include_adult=false&region=regiontest&year=2015&primary_release_year=2015";
        StringBuilder stringBuilder = new StringBuilder( "https://api.themoviedb.org/3/search/movie?" );
        stringBuilder.append( "api_key=" ).append( apiKey );
        stringBuilder.append( "&language=en-US" );
        stringBuilder.append( "&query=" ).append( query );

        if( page > 0 )
        {
            stringBuilder.append( "&page=" ).append( page );
        }

        stringBuilder.append( "&include_adult=" ).append( includeAdult );

        if( region != null && region.length() > 0 )
        {
            stringBuilder.append( "&region=" ).append( region );
        }
        if( year > 0 )
        {
            stringBuilder.append( "&year=" ).append( year );
        }
        if( primaryReleaseYear > 0 )
        {
            stringBuilder.append( "&primary_release_year=" ).append( primaryReleaseYear );
        }
        System.out.println( stringBuilder.toString() );
        return new HttpGet( stringBuilder.toString() );
    }

    public static HttpGet genreSearchRequest( String apiKey )
    {
        return new HttpGet( "https://api.themoviedb.org/3/genre/movie/list?api_key=" + apiKey + "&language=en-US" );
    }
}
