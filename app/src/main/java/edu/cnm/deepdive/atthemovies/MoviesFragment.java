package edu.cnm.deepdive.atthemovies;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.atthemovies.model.Movie;
import edu.cnm.deepdive.atthemovies.viewmodel.MoviesViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {


  private Context context;

  public MoviesFragment() {
    // Required empty public constructor
  }

  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {

    final View view = inflater.inflate(R.layout.fragment_movies, container, false);

    ListView moviesListView = view.findViewById(R.id.movies_list);

    final MoviesViewModel viewModel = ViewModelProviders.of(getActivity()).get(MoviesViewModel.class);

    final ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(context, android.R.layout.simple_list_item_1,
        viewModel.getMovies());
    moviesListView.setAdapter(adapter);

    final Spinner genreSpinner = view.findViewById(R.id.new_movie_genre);
    ArrayAdapter<Movie.Genre> genreAdapter = new ArrayAdapter<>(context,
        android.R.layout.simple_spinner_item);

    Button newMovieButton = view.findViewById(R.id.new_movie_button);
    newMovieButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        EditText newMovieNameEditText = view.findViewById(R.id.new_movie_name);
        EditText newMovieScreenwriter = view.findViewById(R.id.new_movie_screenwriter);
        Movie newMovie = new Movie();
        newMovie.setTitle(newMovieNameEditText.getText().toString());
        newMovie.setScreenwriter(newMovieScreenwriter.getText().toString());
        newMovie.setGenre((Movie.Genre) genreSpinner.getSelectedItem());
        viewModel.addMovie(newMovie);
        adapter.clear();
        adapter.notifyDataSetChanged();
        adapter.addAll(viewModel.getMovies());
        newMovieNameEditText.setText("");
      }
    });

    moviesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //This code opens the actor fragment and passes the movie id that was clicked
        MoviesFragmentDirections.ActionMoviesFragmentToActorsFragment action =
            MoviesFragmentDirections.actionMoviesFragmentToActorsFragment()
                .setMovieId(adapter.getItem(position).getId());
        Navigation.findNavController(view).navigate(action);
      }
    });
    // Inflate the layout for this fragment

    return view;
  }

}
