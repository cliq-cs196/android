package cliq.connie;

public class MyEvents {

}

//MAY NOT GO HERE,
private OnClickListener mCorkyListener = new OnClickListener() {
 public void onClick(View v) {
	 
 }
};

protected void onCreate(Bundle savedValues) {
 Button button = (Button)findViewById(R.id.corky);
 button.setOnClickListener(mCorkyListener);
}