package com.imdb.demo.movie;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kinath on 6/11/2017.
 */

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonPropertyOrder({"genres"})
public class GenreResult
{

    @JsonProperty("genres") private List<Genre> genres = null;
    @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("genres") public List<Genre> getGenres()
    {
        return genres;
    }

    @JsonProperty("genres") public void setGenres( List<Genre> genres )
    {
        this.genres = genres;
    }

    @JsonAnyGetter public Map<String, Object> getAdditionalProperties()
    {
        return this.additionalProperties;
    }

    @JsonAnySetter public void setAdditionalProperty( String name, Object value )
    {
        this.additionalProperties.put( name, value );
    }

}
