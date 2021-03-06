package sv.edu.ues.fia.appserviciosocial;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoTrabajoConsultar extends Activity {

	//Variables globales
		ControlBD auxiliar;	
		private EditText editText1;
		private EditText editText2;
		private EditText editText3;
		//sonidos
		SoundPool soundPool;
		int exito;
		int fracaso;
		 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tipo_trabajo_consultar);
		//Relacionar variables globales con los controles del layout	
				auxiliar = new ControlBD(this);	//constructor de la conexion bd
				editText1=(EditText) findViewById(R.id.editText1);
				editText2=(EditText) findViewById(R.id.edtNombrett);
				editText3=(EditText) findViewById(R.id.editText3);
				//sonidos
		         soundPool = new SoundPool( 2, AudioManager.STREAM_MUSIC , 0);
		         exito = soundPool.load(getApplicationContext(), R.raw.sonido, 0);
		         fracaso = soundPool.load(getApplicationContext(), R.raw.sonido2, 0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tipo_trabajo_consultar, menu);
		return true;
	}
	
	public void consultarTipoTrabajo(View v){
		String idTipoTrabajo = editText1.getText().toString();
		if(idTipoTrabajo.length()==0)
		{
			Toast.makeText(this, "Id Tipo Trabajo inv�lido", Toast.LENGTH_LONG).show();
			return;
			
		}
		auxiliar.abrir();
		TipoTrabajo objTipoTrabajo=auxiliar.consultarTipoTrabajo(idTipoTrabajo);
		auxiliar.cerrar();
		if(objTipoTrabajo==null){
			Toast.makeText(this, "Tipo de Trabajo no encontrado", Toast.LENGTH_LONG).show();
			soundPool.play(fracaso, 1, 1, 1, 0, 1);
			return;
		}else{
			soundPool.play(exito, 1, 1, 1, 0, 1);
			editText2.setText(objTipoTrabajo.getNombre());
			editText3.setText(toString().valueOf(objTipoTrabajo.getValor()));
			
		}
			
		
	}

}
