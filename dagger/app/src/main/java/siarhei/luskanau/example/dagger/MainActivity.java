package siarhei.luskanau.example.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import siarhei.luskanau.example.dagger.component.ComponentA;
import siarhei.luskanau.example.dagger.component.ComponentB;
import siarhei.luskanau.example.dagger.component.ComponentC;
import siarhei.luskanau.example.dagger.component.DaggerComponentA;
import siarhei.luskanau.example.dagger.component.DaggerComponentB;
import siarhei.luskanau.example.dagger.component.DaggerComponentC;
import siarhei.luskanau.example.dagger.target.TargetA;
import siarhei.luskanau.example.dagger.target.TargetB;
import siarhei.luskanau.example.dagger.target.TargetC;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ComponentA componentA = DaggerComponentA.builder()
                .build();
        componentA.inject(new TargetA());

        final ComponentB componentB = DaggerComponentB.builder()
                .componentA(componentA)
                .build();
        componentB.inject(new TargetB());

        final ComponentC componentC = DaggerComponentC.builder()
                .componentB(componentB)
                .build();
        componentC.inject(new TargetC());

        toString();
    }
}
