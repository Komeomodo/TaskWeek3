package com.example.taskweek3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        var fragmentManager = supportFragmentManager

        var frag = FirstFragment()
        var fragTwo = SubFragment2()
        var fragThree = ThirdFrament()

        //Getting id of the various buttons and saving it into new variables
        var addToFragment = findViewById<Button>(R.id.addFragment)
        var  removeFragment = findViewById<Button>(R.id.removeFragment)

        var count = 1  // adding a counter to keep track of the fragments added
        addToFragment.setOnClickListener {
            var transaction = fragmentManager.beginTransaction() //start the transaction to add fragment

            if (count == 1) {
                transaction.add(R.id.frag_container, frag).addToBackStack(null).commit()
                count++
            } else if (count == 2){
                transaction.add(R.id.frag_container, fragTwo).addToBackStack(null).commit()
                count++
            } else if (count == 3){
                transaction.add(R.id.frag_container, fragThree).addToBackStack(null).commit()
                count++
            } else{
                Toast.makeText(applicationContext, "Maximum fragemt reached", Toast.LENGTH_SHORT).show()
            }
        }
//To remove fragment from the stack
        removeFragment.setOnClickListener {
            var transaction = fragmentManager.beginTransaction()
            when (count) {
                2 -> {
                    transaction.remove(frag).commit()
                    count--
                }
                3 -> {
                    transaction.remove(fragTwo).commit()
                    count--
                }
                4 -> {
                    transaction.remove(fragThree).commit()
                    count--
                }
                else -> {
                    Toast.makeText(applicationContext, "All fragments removed successfully ", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}