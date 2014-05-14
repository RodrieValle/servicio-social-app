package sv.edu.ues.fia.appserviciosocial;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProyectoInsertarActivity extends Activity {

	EditText idProyecto;
	EditText idSolicitante;
	EditText idTipoProyecto;
	EditText idEncargado;
	EditText nombre;
	ControlBD helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		helper = new ControlBD(this);
		setContentView(R.layout.activity_proyecto_insertar);
		//idProyecto = (EditText) findViewById(R.id.editCodigoProyecto);
		idSolicitante = (EditText) findViewById(R.id.editCodeSolicitante);
		idTipoProyecto = (EditText) findViewById(R.id.editTipoProyecto);
		idEncargado = (EditText) findViewById(R.id.editCodeEncargadoProyecto);
		nombre = (EditText) findViewById(R.id.editNombreProyecto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.proyecto_insertar, menu);
		return true;
	}

	public void insertarProyecto(View v) {

		//int codeProyecto = Integer.parseInt(idProyecto.getText().toString());
		int codeSolicitante = Integer.parseInt(idSolicitante.getText().toString());
		int codeTipoProyecto = Integer.parseInt(idTipoProyecto.getText().toString());
		int codeEncargado = Integer.parseInt(idEncargado.getText().toString());
		String name = nombre.getText().toString();
		
		
		Proyecto proyecto = new Proyecto();
		//proyecto.setIdProyecto(codeProyecto);
		proyecto.setIdSolicitante(codeSolicitante);
		proyecto.setIdTipoProyecto(codeTipoProyecto);
		proyecto.setIdEncargado(codeEncargado);
		proyecto.setNombre(name);
		
		helper.abrir();
		String regInsertados = helper.insertar(proyecto);
		helper.cerrar();
		Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
	}
	
	public void limpiar(View v){
		//idProyecto.setText("");
		idEncargado.setText("");
		idTipoProyecto.setText("");
		idEncargado.setText("");
		nombre.setText("");
	}

}
