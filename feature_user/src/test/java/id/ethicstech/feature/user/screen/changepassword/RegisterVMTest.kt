package id.ethicstech.feature.user.screen.changepassword

import id.ethicstech.feature.user.screen.register.RegisterVM
import io.mockk.mockk
import org.junit.Test

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Jul/2020
 */
class RegisterVMTest {

    private val vm = RegisterVM(mockk())

    @Test
    fun `Given 62 phone number, When reformat, Then shouldbe returns startWith 0` () {
        // Given
        val phoneNumber = "6281222445115"

        // When
        val result = vm.reformatPhoneNumber(phoneNumber)

        // Then
        assert(result == "081222445115")
    }

    @Test
    fun `Given +62 phone number, When reformat, Then shouldbe returns startWith 0` () {
        // Given
        val phoneNumber = "+6281222445115"

        // When
        val result = vm.reformatPhoneNumber(phoneNumber)

        // Then
        assert(result == "081222445115")
    }

    @Test
    fun `Given 0 phone number, When reformat, Then shouldbe returns it self` () {
        // Given
        val phoneNumber = "081222445115"

        // When
        val result = vm.reformatPhoneNumber(phoneNumber)

        // Then
        assert(result == "081222445115")
    }
}