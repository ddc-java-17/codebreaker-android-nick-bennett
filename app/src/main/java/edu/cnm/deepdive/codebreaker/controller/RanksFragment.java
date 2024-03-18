package edu.cnm.deepdive.codebreaker.controller;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;
import edu.cnm.deepdive.codebreaker.R;
import edu.cnm.deepdive.codebreaker.databinding.FragmentRanksBinding;
import edu.cnm.deepdive.codebreaker.viewmodel.CodebreakerViewModel;
import edu.cnm.deepdive.codebreaker.viewmodel.PreferencesViewModel;
import edu.cnm.deepdive.codebreaker.viewmodel.RankingsViewModel;

@AndroidEntryPoint
public class RanksFragment extends Fragment {

  private FragmentRanksBinding binding;
  private RankingsViewModel rankingsViewModel;

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    binding = FragmentRanksBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ViewModelProvider provider = new ViewModelProvider(requireActivity());
    LifecycleOwner owner = getViewLifecycleOwner();
    rankingsViewModel = provider.get(RankingsViewModel.class);
    rankingsViewModel.getRankings()
        .observe(owner, (rankings) -> {
          // TODO: 2024-03-18 Populate recyclerview with rankings.
        });
    CodebreakerViewModel codebreakerViewModel = provider.get(CodebreakerViewModel.class);
    codebreakerViewModel.getGame()
        .observe(owner, (game) -> {
          // TODO: 2024-03-18 If we have both code length and games threshold, invoke rankingsViewModel.fetch(codeLength, guessThreshold)
        });
    PreferencesViewModel preferencesViewModel = provider.get(PreferencesViewModel.class);
    preferencesViewModel.getPreferredGamesThreshold()
        .observe(owner, (threshold) -> {
          // TODO: 2024-03-18 If we have both code length and games threshold, invoke rankingsViewModel.fetch(codeLength, guessThreshold)
        });
  }

  @Override
  public void onDestroyView() {
    binding = null;
    super.onDestroyView();
  }
  
}