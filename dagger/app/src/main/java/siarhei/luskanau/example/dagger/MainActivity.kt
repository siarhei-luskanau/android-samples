package siarhei.luskanau.example.dagger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import siarhei.luskanau.example.dagger.component.ComponentA
import siarhei.luskanau.example.dagger.component.ComponentB
import siarhei.luskanau.example.dagger.component.ComponentC
import siarhei.luskanau.example.dagger.component.DaggerComponentA
import siarhei.luskanau.example.dagger.component.DaggerComponentB
import siarhei.luskanau.example.dagger.component.DaggerComponentC
import siarhei.luskanau.example.dagger.target.TargetA
import siarhei.luskanau.example.dagger.target.TargetB
import siarhei.luskanau.example.dagger.target.TargetC

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val componentA:ComponentA = DaggerComponentA.builder()
                .build()
        componentA.inject(TargetA())

        val componentB:ComponentB = DaggerComponentB.builder()
                .componentA(componentA)
                .build()
        componentB.inject(TargetB())

        val componentC:ComponentC = DaggerComponentC.builder()
                .componentB(componentB)
                .build()
        componentC.inject(TargetC())

        toString()
    }
}
