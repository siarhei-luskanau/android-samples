package siarhei.luskanau.example.dagger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import siarhei.luskanau.example.dagger.component.*
import siarhei.luskanau.example.dagger.target.TargetA
import siarhei.luskanau.example.dagger.target.TargetB
import siarhei.luskanau.example.dagger.target.TargetC

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val componentA: ComponentA = DaggerComponentA.builder()
                .build()
        componentA.inject(TargetA())

        val componentB: ComponentB = DaggerComponentB.builder()
                .componentA(componentA)
                .build()
        componentB.inject(TargetB())

        val componentC: ComponentC = DaggerComponentC.builder()
                .componentB(componentB)
                .build()
        componentC.inject(TargetC())
    }
}
