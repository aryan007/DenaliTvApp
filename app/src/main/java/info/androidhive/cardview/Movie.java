package info.androidhive.cardview;

import java.io.Serializable;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Movie implements Serializable {
    private String id;
    private String name;
    private int numOfEpisodes;
    private int thumbnail;
    private int detailThumbnail;
    private String url;
    private boolean isCached = true;

    public Movie() {
    }

    public Movie(String id, String name, int numOfEpisodes, int thumbnail, int detailThumbnail, String url) {
        this.name = name;
        this.numOfEpisodes = numOfEpisodes;
        this.thumbnail = thumbnail;
        this.detailThumbnail = detailThumbnail;
        this.url = url;
        this.id = id;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfEpisodes() {
        return numOfEpisodes;
    }

    public void setNumOfEpisodes(int numOfEpisodes) {
        this.numOfEpisodes = numOfEpisodes;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getDetailThumbnail() {
        return detailThumbnail;
    }

    public void setDetailThumbnail(int detailThumbnail) {
        this.detailThumbnail = detailThumbnail;
    }

    public boolean isCached() {
        return isCached;
    }

    public void setCached(boolean cached) {
        isCached = cached;
    }
}
