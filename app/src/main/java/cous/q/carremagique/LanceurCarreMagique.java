package cous.q.carremagique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LanceurCarreMagique extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanceur_carre_magique);
    }

    public void quitterApp ( View v )
    {
        System.exit(0);
    }

    public void lancerApp ( View v )
    {
        Intent intent = new Intent(LanceurCarreMagique.this, MainActivity.class);
        startActivity(intent);
    }

}
