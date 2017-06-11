package com.imdb.demo;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

        String genreSearchUrl = "https://api.themoviedb.org/3/genre/movie/list?api_key="+API_KEY+"&language=en-US";


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
