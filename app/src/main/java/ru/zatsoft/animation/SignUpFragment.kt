package ru.zatsoft.animation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast

class SignUpFragment : Fragment() {
    private lateinit var animation1: Animation
    private lateinit var animation: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userNameET = view.findViewById<EditText>(R.id.etUserNameSignUp)
        val userPassET = view.findViewById<EditText>(R.id.etUserPassSignUp)
        val loginUpBTN = view.findViewById<Button>(R.id.btnLoginSignUp)
        animation = AnimationUtils.loadAnimation(requireContext(),R.anim.fall)
        val upperLL = view.findViewById<LinearLayout>(R.id.llUp)
        upperLL.startAnimation(animation)
        animation1 = AnimationUtils.loadAnimation(requireContext(),R.anim.rise)
        val downLL = view.findViewById<LinearLayout>(R.id.llDown)
        downLL.startAnimation(animation1)
        loginUpBTN.setOnClickListener {
            val userNameSignUp = userNameET.text.toString()
            val userPassSignUp = userPassET.text.toString()
            if(userNameSignUp.isEmpty() || userPassSignUp.isEmpty()) {
                Toast.makeText(context, "Данные не введены", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            fragmentManager?.beginTransaction()?.replace(R.id.containerID, MainFragment())?.commit()
            Toast.makeText(context,"Вы успешно зарегистированы", Toast.LENGTH_LONG).show()
        }
    }
}