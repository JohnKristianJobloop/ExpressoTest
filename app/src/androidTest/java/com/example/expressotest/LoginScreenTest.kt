package com.example.expressotest

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * espresso fungerer i en tre step loop:
 *  1. Finn et view
 *  2. Perform en action
 *  3. Assert et resultat basert på action
 *
 *  Vi ser nedenfor at vi bruker LoginActivity, for å finne et View som matcher det LoginActivity eksponerer.
 *  Vi finner subViews knyttet til mainView, og performer en action på disse subviewene.
 *  Siste steg er at vi asserter noe om Viewet etter våre actions er performed.
 */


//Denne attributten forteller at vi bruker Android sin innebygde testrunner

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    //Vi finner hvilken Class som skal teste, vi kan tenkte dette på at vi må finne "reglene" for vårt View.
    //Denne passer på at korrekt "Activity" blir initialisert på vår emulator, slik at den kan testes.
    //Den initialiserer og stenger viewet for hver test.
    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun successfulLogin_navigatesToWelcomeScreen(){

        //Her finner vi elementet i Viewet vårt som matcher id emailInput
        onView(withId(R.id.emailInput))
            //Vi forteller da espresso sin ViewInteraction at den skal performe følgende: skriv "user@email.com" i elementet. steng så softKeyboard elementet.
            .perform(typeText("user@example.com"), closeSoftKeyboard())

        //Vi repeterer samme funksjon som ovenfor, men på elementet med id passwordInput
        onView(withId(R.id.passwordInput))
            .perform(typeText("password123"), closeSoftKeyboard())

        //Vi finner så elementet med id loginButton
        onView(withId(R.id.loginButton))
            //og ber espresso merforme click()
            .perform(click())

        //Vi leter så etter et view som matcher id welcomeText
        onView(withId(R.id.welcomeText))
            //og asserter via espresso sin ViewAsserter at dette viewet er displayet.
            .check(matches(isDisplayed()))
    }

    @Test
    fun emptyPassword_showsError(){
        onView(withId(R.id.emailInput))
            .perform(typeText("user@example.com"), closeSoftKeyboard())

        onView(withId(R.id.loginButton))
            .perform(click())

        //Vi kan også asserte ting som at en error text er displayet, hvis modellen tillater det.
        //elementet med id passwordInput støtter errortext display, samt vi setter .error på elementet i LoginActivity
        //Til texten
        onView(withId(R.id.passwordInput))
            //Vi kan derfor asserte at den eksisterer etter vi prøver å klikke uten å fylle inn et passord.
            .check(matches(hasErrorText("Password required")))
    }
}