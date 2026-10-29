package com.example.lab_8


import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WizardTest {
    private lateinit var evilWizard: Wizard

    // Set up a new wizard before every single test
    @Before
    fun setUp() {
        evilWizard = Wizard("Evil Wizard", 30, 10)
    }

    @Test
    fun castSpell_explosion_successful() {
        val damageDealt = evilWizard.castSpell("Explosion")

        assertEquals(30, damageDealt) // spellPower of 10 * 3 = 30

        assertEquals(20, evilWizard.mana) // mana of 30 - 10 = 20
    }

    @Test
    fun castSpell_explosion_unsuccessful() {
        evilWizard.mana = 5 // Evil Wizard does not have enough mana to cast "Explosion"
        val damageDealt = evilWizard.castSpell("Explosion")

        assertEquals(0, damageDealt)

        assertEquals(5, evilWizard.mana) // mana still is 5
    }

    @Test
    fun restoreMana_amountAddedExceedsOneHundred_manaSetToOneHundred() {
        evilWizard.restoreMana(80)

        assertEquals(100, evilWizard.mana)

    }

    @Test
    fun train_hasEnoughMana() {
        val mana = evilWizard.mana
        val spellPower = evilWizard.spellPower

        evilWizard.train()  // wizard already has enough mana
        assertEquals(mana - 5, evilWizard.mana)
        assertEquals(spellPower + 1, evilWizard.spellPower)
    }

    @Test
    fun train_doesNotHaveEnoughMana() {
        evilWizard.mana = 5 // not enough mana

        val mana = evilWizard.mana
        val spellPower = evilWizard.spellPower

        evilWizard.train()  // stats shouldn't change
        assertEquals(mana, evilWizard.mana)
        assertEquals(spellPower, evilWizard.spellPower)
    }
}