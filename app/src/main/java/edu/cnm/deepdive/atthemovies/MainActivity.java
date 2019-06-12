package edu.cnm.deepdive.atthemovies;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.atthemovies.viewmodel.MoviesViewModel;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onStop() {
    super.onStop();
    MoviesViewModel viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
    viewModel.saveData(this);
  }
}
