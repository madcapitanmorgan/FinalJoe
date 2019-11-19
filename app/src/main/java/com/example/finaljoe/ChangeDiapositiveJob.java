package com.example.finaljoe;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static com.example.finaljoe.StartPresentationActivity.currentDiapositiva;

public class ChangeDiapositiveJob extends JobService
{
    private static DiapositivaRepository repository;
    @Override
    public boolean onStartJob(JobParameters params)
    {
        repository = new DiapositivaRepository(this.getApplication());
        Diapositiva diapositiva = new Diapositiva("","",0);
        diapositiva.setDiapositivaId(currentDiapositiva+1);
        diapositiva = repository.search(diapositiva);

        if(diapositiva != null)
        {

            Toast.makeText(this,"Cambio de Diapositiva",Toast.LENGTH_SHORT).show();

            currentDiapositiva++;
            Intent intent = new Intent(this, StartPresentationActivity.class);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            jobFinished(params,false);
        }
        else
        {
            Toast.makeText(this,"Fin de la presentación",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }


        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params)
    {
        return false;
    }
}
