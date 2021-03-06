package sv.edu.ues.fia.appserviciosocial;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoProyectoEliminarActivity extends Activity {
	
	EditText txtTipoProyecto;
	ControlBD helper;
	//sonidos
		SoundPool soundPool;
		int exito;
		
		 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tipo_proyecto_eliminar);
		
		txtTipoProyecto = (EditText)findViewById(R.id.txtTipoProyectoEliminar);
		helper = new ControlBD(this);
		//sonidos
        soundPool = new SoundPool( 2, AudioManager.STREAM_MUSIC , 0);
        exito = soundPool.load(getApplicationContext(), R.raw.sonido, 0);
       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tipo_proyecto_eliminar, menu);
		return true;
	}
	
	public void eliminarTipoProyecto(View v){
		String mensaje = "";
		TipoProyecto tipoProyecto = new TipoProyecto();
		tipoProyecto.setIdTipoProyecto(Integer.valueOf(txtTipoProyecto.getText().toString()));
		helper.abrir();
		mensaje = helper.eliminar(tipoProyecto);
		helper.cerrar();
		Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
		soundPool.play(exito, 1, 1, 1, 0, 1);
	}

}
