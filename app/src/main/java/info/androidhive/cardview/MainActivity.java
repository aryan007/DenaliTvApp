package info.androidhive.cardview;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRecyclerViewItemClickListener {

    private RecyclerView recyclerView;
    private AlbumsAdapter adapter;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        movieList = new ArrayList<>();
        adapter = new AlbumsAdapter(this, movieList, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.sonyliv,
                R.drawable.hotstar,
                R.drawable.album3,
                R.drawable.album4,
                R.drawable.album5,
                R.drawable.album6,
                R.drawable.album7,
                R.drawable.album8,
                R.drawable.album9,
                R.drawable.album10,
                R.drawable.album11};

        Movie a = new Movie("1", "True Romance", 13, covers[0], R.drawable.powder, "https://manifest.prod.boltdns.net/manifest/v1/hls/v5/aes128/5182475815001/261fac78-8dcf-417e-a9c1-47b1e66e873f/10s/master.m3u8?fastly_token=NWFlMjI3ZWRfNWRiNjBiMDlkYWE3MDRmMTM3YWU1M2VlYTAyMGE4ZDc0NzkzZDA0YzY2YWZmOTU1NDJlNzU4ZjdiMmZkYWNhNw%3D%3D");
        movieList.add(a);
        a.setCached(false);

        a = new Movie("2", "Xscpae", 8, covers[1], R.drawable.judwaa, "https://staragvod3-vh.akamaihd.net/i/videos/worldwide/movies/hindi/1770016732_3/1770016732_,180,400,800,1300,2000,3000,4500,6000,_STAR.mp4.csmil/master.m3u8?subtitle_identifier=1770016732&hdnea=st=1523039102~exp=1523039702~acl=/*~hmac=9030c48979face0a19aacd43f9cf5fa15d84e0e35f156739062fcae526841919");
        movieList.add(a);

        a = new Movie("3", "Maroon 5", 11, covers[2], R.drawable.powder, "https://manifest.prod.boltdns.net/manifest/v1/hls/v5/aes128/5182475815001/261fac78-8dcf-417e-a9c1-47b1e66e873f/10s/master.m3u8?fastly_token=NWFlMjI3ZWRfNWRiNjBiMDlkYWE3MDRmMTM3YWU1M2VlYTAyMGE4ZDc0NzkzZDA0YzY2YWZmOTU1NDJlNzU4ZjdiMmZkYWNhNw%3D%3D");
        movieList.add(a);

        a = new Movie("4", "Born to Die", 12, covers[3], R.drawable.powder, "https://manifest.prod.boltdns.net/manifest/v1/hls/v5/aes128/5182475815001/261fac78-8dcf-417e-a9c1-47b1e66e873f/10s/master.m3u8?fastly_token=NWFlMjI3ZWRfNWRiNjBiMDlkYWE3MDRmMTM3YWU1M2VlYTAyMGE4ZDc0NzkzZDA0YzY2YWZmOTU1NDJlNzU4ZjdiMmZkYWNhNw%3D%3D");
        movieList.add(a);

        a = new Movie("5", "Honeymoon", 14, covers[4], R.drawable.powder, "https://manifest.prod.boltdns.net/manifest/v1/hls/v5/aes128/5182475815001/261fac78-8dcf-417e-a9c1-47b1e66e873f/10s/master.m3u8?fastly_token=NWFlMjI3ZWRfNWRiNjBiMDlkYWE3MDRmMTM3YWU1M2VlYTAyMGE4ZDc0NzkzZDA0YzY2YWZmOTU1NDJlNzU4ZjdiMmZkYWNhNw%3D%3D");
        movieList.add(a);

        a = new Movie("6", "I Need a Doctor", 1, covers[5], R.drawable.powder, "https://manifest.prod.boltdns.net/manifest/v1/hls/v5/aes128/5182475815001/261fac78-8dcf-417e-a9c1-47b1e66e873f/10s/master.m3u8?fastly_token=NWFlMjI3ZWRfNWRiNjBiMDlkYWE3MDRmMTM3YWU1M2VlYTAyMGE4ZDc0NzkzZDA0YzY2YWZmOTU1NDJlNzU4ZjdiMmZkYWNhNw%3D%3D");
        movieList.add(a);

        a = new Movie("7", "Loud", 11, covers[6], R.drawable.powder, "https://manifest.prod.boltdns.net/manifest/v1/hls/v5/aes128/5182475815001/261fac78-8dcf-417e-a9c1-47b1e66e873f/10s/master.m3u8?fastly_token=NWFlMjI3ZWRfNWRiNjBiMDlkYWE3MDRmMTM3YWU1M2VlYTAyMGE4ZDc0NzkzZDA0YzY2YWZmOTU1NDJlNzU4ZjdiMmZkYWNhNw%3D%3D");
        movieList.add(a);

        a = new Movie("8", "Legend", 14, covers[7], R.drawable.powder, "https://manifest.prod.boltdns.net/manifest/v1/hls/v5/aes128/5182475815001/261fac78-8dcf-417e-a9c1-47b1e66e873f/10s/master.m3u8?fastly_token=NWFlMjI3ZWRfNWRiNjBiMDlkYWE3MDRmMTM3YWU1M2VlYTAyMGE4ZDc0NzkzZDA0YzY2YWZmOTU1NDJlNzU4ZjdiMmZkYWNhNw%3D%3D");
        movieList.add(a);

        a = new Movie("9", "Hello", 11, covers[8], R.drawable.powder, "https://manifest.prod.boltdns.net/manifest/v1/hls/v5/aes128/5182475815001/261fac78-8dcf-417e-a9c1-47b1e66e873f/10s/master.m3u8?fastly_token=NWFlMjI3ZWRfNWRiNjBiMDlkYWE3MDRmMTM3YWU1M2VlYTAyMGE4ZDc0NzkzZDA0YzY2YWZmOTU1NDJlNzU4ZjdiMmZkYWNhNw%3D%3D");
        movieList.add(a);

        a = new Movie("10", "Greatest Hits", 17, covers[9], R.drawable.powder, "https://manifest.prod.boltdns.net/manifest/v1/hls/v5/aes128/5182475815001/261fac78-8dcf-417e-a9c1-47b1e66e873f/10s/master.m3u8?fastly_token=NWFlMjI3ZWRfNWRiNjBiMDlkYWE3MDRmMTM3YWU1M2VlYTAyMGE4ZDc0NzkzZDA0YzY2YWZmOTU1NDJlNzU4ZjdiMmZkYWNhNw%3D%3D");
        movieList.add(a);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRecyclerViewItemClicked(Movie movie, int position) {
        final Intent detailActivityIntent = new Intent(this, DetailPageActivity.class);
        detailActivityIntent.putExtra("movie", movieList.get(position));
        startActivity(detailActivityIntent);
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
