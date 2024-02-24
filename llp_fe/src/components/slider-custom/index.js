import React, { memo, useEffect, useState, useRef, forwardRef } from 'react'
import Slider from 'react-slick'

let firstClientX, clientX
// fix lỗi khi đang scroll ngang slider thì có thể scroll browser
const SliderCustom = (({ children, ...props }, ref) => {
  let containerRef = useRef()

  const preventTouch = (e) => {
    const minValue = 5 // threshold

    clientX = e.touches[0].clientX - firstClientX

    // Vertical scrolling does not work when you start swiping horizontally.
    if (Math.abs(clientX) > minValue) {
      e.preventDefault()
      e.returnValue = false

      return false
    }
  }

  const touchStart = (e) => {
    firstClientX = e.touches[0].clientX
  }

  useEffect(() => {
    if (containerRef.current) {
      containerRef.current.addEventListener('touchstart', touchStart)
      containerRef.current.addEventListener('touchmove', preventTouch, {
        passive: false,
      })
    }

    return () => {
      if (containerRef.current) {
        containerRef.current.removeEventListener('touchstart', touchStart)
        containerRef.current.removeEventListener('touchmove', preventTouch, {
          passive: false,
        })
      }
    }
  })
  return (
    <div ref={containerRef}>
      <Slider {...props} speed={100}>
        {children}
      </Slider>
    </div>
  )
})

export default memo(SliderCustom)
