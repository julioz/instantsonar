package com.sample.instantsonar.artists;

import com.sample.instantsonar.artists.viewmodel.Track;
import com.squareup.picasso.Picasso;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.TrackViewHolder> {

    private List<Track> tracks;

    public TracksAdapter(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.row_track, parent, false);

        return new TrackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        Track track = tracks.get(position);

        Picasso.with(holder.artworkView.getContext()).load(track.getArtworkUrl()).into(holder.artworkView);
        holder.title.setText(track.getTitle());
        holder.playCount.setText(String.valueOf(track.getPlaybackCount()));
        holder.favCount.setText(String.valueOf(track.getFavoritingsCount()));
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    static class TrackViewHolder extends RecyclerView.ViewHolder {
        ImageView artworkView;
        TextView title;
        TextView playCount;
        TextView favCount;

        TrackViewHolder(View view) {
            super(view);
            artworkView = (ImageView) view.findViewById(R.id.row_track_artwork);
            title = (TextView) view.findViewById(R.id.row_track_title);
            playCount = (TextView) view.findViewById(R.id.row_track_play_count);
            favCount = (TextView) view.findViewById(R.id.row_track_favorite_count);
        }
    }
}
