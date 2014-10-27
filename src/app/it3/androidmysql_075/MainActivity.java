package app.it3.androidmysql_075;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText edit1, edit2, edit3;
	private Button btn1;
	private ProgressDialog pDialog;
	private static String url_create_student = "http://www.sawasdeemall.com/android/create_student.php";
	JSONParser jsonParser = new JSONParser();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edit1 = (EditText) findViewById(R.id.editText2_stu_id);
		edit2 = (EditText) findViewById(R.id.editText1_name);
		edit3 = (EditText) findViewById(R.id.editText3_tel);
		btn1 = (Button) findViewById(R.id.save);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new CreateNewStudent().execute();

			}
		});
	}

	class CreateNewStudent extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Createing Student.....");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			String Stu_id = edit1.getText().toString();
			String name = edit2.getText().toString();
			String tel = edit3.getText().toString();

			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("stu_id", Stu_id));
			list.add(new BasicNameValuePair("name", name));
			list.add(new BasicNameValuePair("tel", tel));

			JSONObject json = jsonParser.makeHttpRequest(url_create_student,
					"POST", list);

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
		pDialog.dismiss();
		}
	}

}
