package com.example.applitest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.applitest.databinding.FragmentFirstBinding;
import com.example.applitest.network.CheckIpAndPort;
import com.example.applitest.network.CheckIpAndPortInterface;

import org.w3c.dom.Text;

public class FirstFragment extends Fragment implements CheckIpAndPortInterface.NotificationListener {

    private FragmentFirstBinding binding;

    private TextView textViewStatus;


    CheckIpAndPort checkIpAndPort = new CheckIpAndPort();


    CheckIpAndPortInterface.StateChecking stateChecking = CheckIpAndPortInterface.StateChecking.INIT;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        textViewStatus = binding.textViewStatus;

        checkIpAndPort.addListener(this);


        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonLaunchScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NavHostFragment.findNavController(FirstFragment.this)
                   //   .navigate(R.id.action_FirstFragment_to_SecondFragment);
                if(stateChecking == CheckIpAndPortInterface.StateChecking.INIT){
                    checkIpAndPort.startChecking();
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onStateCheckingChanged() {
        stateChecking = checkIpAndPort.getState();
        refreshUi();

    }

    private void refreshUi() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                switch (stateChecking){
                    case INIT:
                        textViewStatus.setText("Cliquer sur le bouton pour lancer le scan");
                        break;
                    case CHECKING:
                        textViewStatus.setText("Check en cours");
                        break;
                    case FINISH:
                        textViewStatus.setText("Fini");
                        break;
                    case ERROR:
                        textViewStatus.setText("Une erreur s'est produite, merci de relancer");
                        break;
                }

            }
        });
    }
}