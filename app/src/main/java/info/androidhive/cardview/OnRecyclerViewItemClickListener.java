package info.androidhive.cardview;

public interface OnRecyclerViewItemClickListener {
    /**
     * Called when any item with in recyclerview or any item with in item
     * clicked
     *
     * @param position The position of the item
     */
    public void onRecyclerViewItemClicked(Movie movie, int position);
}
