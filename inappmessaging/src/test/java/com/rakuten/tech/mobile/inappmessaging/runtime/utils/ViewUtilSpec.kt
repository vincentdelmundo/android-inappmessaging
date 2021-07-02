package com.rakuten.tech.mobile.inappmessaging.runtime.utils

import android.content.Context
import android.content.res.Resources
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.rakuten.tech.mobile.inappmessaging.runtime.BaseTest
import com.rakuten.tech.mobile.inappmessaging.runtime.data.enums.SlideFromDirectionType
import org.amshove.kluent.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Test class for ViewUtil.
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ViewUtilSpec : BaseTest() {

    @Test
    fun `should have correct animation duration`() {
        val animation = ViewUtil.getSlidingAnimation(
                ApplicationProvider.getApplicationContext(),
                SlideFromDirectionType.BOTTOM)
        animation?.duration shouldBeEqualTo 400L
    }

    @Test
    fun `should return null if invalid animation`() {
        val mockContext = Mockito.mock(Context::class.java)
        val mockResource = Mockito.mock(Resources::class.java)
        When calling mockContext.resources itReturns mockResource
        When calling mockResource.getAnimation(any()) itThrows Resources.NotFoundException("test")
        val animation = ViewUtil.getSlidingAnimation(mockContext, SlideFromDirectionType.BOTTOM)
        animation.shouldBeNull()
    }
}
