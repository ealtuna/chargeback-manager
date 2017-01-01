(if module? and module.exports then require('angular') else window.angular).module("angular-squire").run(["$templateCache", ($templateCache) ->
    $templateCache.put('angular-squire-icon-attachment', """
        <svg version="1.1"  xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
        viewBox="0 0 125 125" style="enable-background:new 0 0 125 125;" xml:space="preserve">
        <g>
        <g>
        <path  d="M54.3,34.1c5.2-5.2,10.3-10.4,15.6-15.4c3.8-3.6,8.5-5.5,13.7-6.1c11.8-1.6,23.2,4.9,27.9,15.9
        c1.8,4.1,2.4,8.4,2,12.9c-0.6,6.3-3.2,11.8-7.7,16.3C99.5,63.8,93.3,70.1,87,76.3c-4.1,4-8.9,6.6-14.6,7.4
        c-10.8,1.7-21.2-3.3-26.6-12.2c-0.2-0.4-0.3-0.7,0.1-1c2.7-2.7,5.4-5.4,8.1-8.1c0.4-0.4,0.9-0.7,1.5-1.1c0.1,0.3,0.2,0.5,0.3,0.6
        c1.9,5.2,5.7,8.2,11.2,8.8c4.1,0.5,7.7-0.8,10.6-3.7C84,60.8,90.4,54.4,96.7,48c3.7-3.8,4.7-8.4,3-13.4c-1.6-5-5.2-8-10.3-8.9
        c-4.4-0.8-8.3,0.4-11.6,3.5c-2.2,2.1-4.3,4.3-6.4,6.4c-0.3,0.3-0.5,0.3-0.8,0.2c-4.2-1.6-8.6-2.2-13.2-2
        C56.4,33.9,55.4,34,54.3,34.1z"/>
        <path  d="M81.7,53.4c-0.2,0.2-0.3,0.4-0.5,0.5c-2.7,2.7-5.4,5.4-8.2,8.1c-0.4,0.4-0.9,0.7-1.4,1.1
        c-0.1-0.3-0.2-0.5-0.2-0.7c-2-5.2-5.7-8.2-11.2-8.8c-4-0.5-7.6,0.7-10.5,3.6C43.3,63.7,36.8,70,30.5,76.5c-3.7,3.8-4.6,8.4-3,13.4
        c1.6,4.9,5.1,7.9,10.3,8.9c4.5,0.8,8.4-0.4,11.6-3.5c2.2-2.1,4.3-4.2,6.4-6.4c0.3-0.3,0.6-0.4,1-0.2c4.2,1.5,8.5,2.2,12.9,1.9
        c1-0.1,2-0.1,3.1-0.2c-0.1,0.1-0.1,0.2-0.2,0.3c-4.7,4.7-9.4,9.5-14.3,14.2c-4,3.9-8.8,6.2-14.3,7c-13.8,2-26.7-6.7-29.7-20.4
        c-2-9,0.2-17.2,6.5-23.9c6.5-6.8,13.2-13.4,19.9-20c4-3.9,8.9-6.2,14.4-6.9c9.1-1.1,16.9,1.6,23.2,8.3
        C79.6,50.4,80.6,51.9,81.7,53.4z"/>
        </g>
        </g>
        </svg>""")

    $templateCache.put('angular-squire-icon-bold', """
        <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
        viewBox="0 0 125 125" style="enable-background:new 0 0 125 125;" xml:space="preserve">
        <g>
        <path  d="M100.7,37.7c0,3.1-0.4,5.8-1.3,8.1c-0.8,2.3-2,4.3-3.6,6c-1.6,1.7-3.4,3.1-5.6,4.3c-2.2,1.2-4.6,2.2-7.2,3v0.7
        c3.2,0.8,6.2,1.9,8.9,3.4c2.7,1.4,5,3.2,6.9,5.3c1.9,2.1,3.4,4.5,4.5,7.2c1.1,2.7,1.6,5.8,1.6,9.2c0,9.2-3.4,16.1-10.1,20.6
        c-6.7,4.5-16.7,6.8-30,6.8H19.7v-7.5h5.7c1,0,2-0.1,2.9-0.3c0.9-0.2,1.7-0.6,2.3-1.3c0.6-0.6,1.2-1.5,1.5-2.7
        c0.4-1.2,0.6-2.8,0.6-4.8V28.6c0-1.9-0.2-3.4-0.6-4.5c-0.4-1.2-0.9-2.1-1.6-2.7c-0.7-0.6-1.4-1.1-2.3-1.3c-0.9-0.3-1.8-0.4-2.8-0.4
        h-5.7v-7.4h40.9c13.3,0,23.3,2.1,30,6.2S100.7,29,100.7,37.7z M54.8,55.7h5.3c3.3,0,6-0.3,8.2-1c2.2-0.7,3.9-1.7,5.2-3.2
        c1.3-1.4,2.2-3.3,2.8-5.6c0.5-2.3,0.8-5,0.8-8.2c0-3.2-0.3-5.9-0.9-8c-0.6-2.1-1.6-3.9-3-5.2c-1.4-1.3-3.1-2.3-5.3-2.8
        c-2.2-0.6-4.9-0.9-8-0.9h-5.1V55.7z M54.8,103.7h9.3c3.1,0,5.7-0.3,7.9-1c2.2-0.7,4-1.8,5.3-3.3c1.4-1.5,2.4-3.5,3-6
        c0.6-2.5,0.9-5.5,0.9-9c0-3.4-0.3-6.4-0.8-8.9c-0.5-2.5-1.5-4.6-2.8-6.3c-1.3-1.6-3.1-2.9-5.2-3.7c-2.1-0.8-4.8-1.2-8.1-1.2h-9.6
        V103.7z"/>
        </g>
        </svg>""")

    $templateCache.put('angular-squire-icon-italic', """
        <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
        viewBox="0 0 125 125" style="enable-background:new 0 0 125 125;" xml:space="preserve">
        <g>
        <path  d="M27.9,112.3l1.5-7.5h1.8c1.5,0,2.9-0.1,4.3-0.3c1.4-0.2,2.7-0.6,3.8-1.2c1.1-0.6,2.1-1.4,3-2.5
        c0.8-1.1,1.4-2.6,1.8-4.4l14.6-68.8c0.2-0.5,0.3-1.1,0.4-1.7c0.1-0.6,0.2-1.1,0.2-1.5c0-1-0.3-1.8-0.8-2.5
        c-0.5-0.6-1.2-1.1-2.1-1.5c-0.9-0.3-2-0.6-3.2-0.7c-1.3-0.1-2.6-0.2-4.1-0.2h-1.8l1.5-7.4h48.4l-1.6,7.4h-1.8c-1.6,0-3,0.1-4.5,0.3
        s-2.7,0.6-3.9,1.2c-1.2,0.6-2.2,1.5-3.1,2.6c-0.9,1.1-1.5,2.6-1.8,4.4L66.1,96.9c-0.1,0.5-0.2,1.1-0.3,1.7
        c-0.1,0.6-0.2,1.1-0.2,1.5c0,1,0.3,1.8,0.8,2.4c0.5,0.6,1.2,1.1,2.1,1.4c0.9,0.3,2,0.6,3.2,0.7c1.3,0.1,2.6,0.2,4.1,0.2h1.8
        l-1.6,7.5H27.9z"/>
        </g>
        </svg>""")

    $templateCache.put('angular-squire-icon-ul', """
        <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
        viewBox="0 0 125 125" style="enable-background:new 0 0 125 125;" xml:space="preserve">
        <g>
        <rect x="38.6" y="29.3"  width="73.9" height="12.3"/>
            <rect x="38.6" y="56"  width="73.9" height="12.3"/>
            <rect x="38.6" y="82.6"  width="73.9" height="12.3"/>
            <circle  cx="21.5" cy="35.5" r="8.9"/>
            <circle  cx="21.5" cy="62.2" r="8.9"/>
            <circle  cx="21.5" cy="88.8" r="8.9"/>
            </g>
            </svg>""")

    $templateCache.put('angular-squire-icon-ol', """
        <svg version="1.1"  xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
        viewBox="0 0 125 125" style="enable-background:new 0 0 125 125;" xml:space="preserve">
        <g>
        <g>
        <g>
        <path  d="M18.1,87.5h1.2c0.3,0,0.7,0,1-0.1c0.3,0,0.6-0.1,0.9-0.3c0.3-0.1,0.5-0.3,0.6-0.6c0.2-0.2,0.2-0.6,0.2-1
        c0-0.5-0.2-0.9-0.6-1.3c-0.4-0.3-0.9-0.5-1.5-0.5c-0.6,0-1,0.2-1.4,0.5c-0.4,0.3-0.6,0.7-0.7,1.1l-3.9-0.8
        c0.2-0.7,0.5-1.4,0.9-1.9c0.4-0.5,0.8-0.9,1.4-1.2c0.5-0.3,1.1-0.6,1.7-0.7c0.6-0.2,1.3-0.2,2-0.2c0.7,0,1.5,0.1,2.2,0.3
        c0.7,0.2,1.3,0.5,1.8,0.9c0.5,0.4,0.9,0.9,1.2,1.5c0.3,0.6,0.5,1.3,0.5,2.1c0,0.9-0.2,1.7-0.7,2.4c-0.5,0.7-1.2,1.1-2.1,1.3V89
        c0.5,0.1,1,0.2,1.3,0.5c0.4,0.2,0.7,0.5,1,0.9c0.3,0.3,0.5,0.7,0.6,1.2c0.1,0.4,0.2,0.9,0.2,1.4c0,0.8-0.2,1.6-0.5,2.2
        c-0.3,0.6-0.8,1.2-1.3,1.6c-0.5,0.4-1.2,0.7-1.9,0.9c-0.7,0.2-1.5,0.3-2.3,0.3c-1.5,0-2.8-0.3-3.9-1c-1.1-0.7-1.8-1.8-2.2-3.3
        l3.8-0.9c0.1,0.6,0.4,1.1,0.8,1.4c0.4,0.3,1,0.5,1.7,0.5c0.8,0,1.4-0.2,1.7-0.6c0.4-0.4,0.6-0.9,0.6-1.6c0-0.5-0.1-0.8-0.3-1.1
        c-0.2-0.3-0.5-0.5-0.8-0.6c-0.3-0.1-0.7-0.2-1.1-0.2c-0.4,0-0.8,0-1.2,0h-0.8V87.5z"/>
        </g>
        </g>
        <rect x="37.4" y="28.3"  width="75.8" height="12.6"/>
            <rect x="37.4" y="55.7"  width="75.8" height="12.6"/>
            <rect x="37.4" y="83"  width="75.8" height="12.6"/>
            <g>
            <path  d="M18.7,30l-3.5,3.1l-2.1-2.4l5.7-4.8h3.4v17.4h-3.5V30z"/>
            </g>
            <g>
            <path  d="M13.8,66.8l6.7-6c0.3-0.3,0.7-0.7,1-1c0.3-0.4,0.5-0.8,0.5-1.4c0-0.6-0.2-1-0.6-1.4c-0.4-0.3-0.9-0.5-1.5-0.5
        c-0.7,0-1.2,0.2-1.6,0.6c-0.4,0.4-0.6,0.9-0.6,1.5L14,58.5c0-0.9,0.2-1.7,0.6-2.3c0.3-0.7,0.7-1.2,1.3-1.6c0.5-0.4,1.2-0.8,1.9-1
        c0.7-0.2,1.5-0.3,2.3-0.3c0.8,0,1.5,0.1,2.2,0.3c0.7,0.2,1.3,0.5,1.8,1c0.5,0.4,0.9,1,1.2,1.6c0.3,0.6,0.4,1.4,0.4,2.2
        c0,0.5-0.1,1-0.2,1.5c-0.1,0.4-0.3,0.8-0.5,1.2c-0.2,0.4-0.4,0.7-0.7,1c-0.3,0.3-0.6,0.6-0.9,0.9l-5.2,4.5h7.6v3.2h-12V66.8z"/>
        </g>
        </g>
        </svg>""")

    $templateCache.put('angular-squire-icon-underline', """
        <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
        viewBox="0 0 125 125" style="enable-background:new 0 0 125 125;" xml:space="preserve">
        <g>
        <path d="M106.6,18.8h-5.3c-0.8,0-1.6,0.1-2.4,0.3c-0.8,0.2-1.4,0.6-2,1.1c-0.6,0.6-1,1.4-1.3,2.4
        c-0.3,1-0.5,2.4-0.5,4.2v47.9c0,4.1-0.6,7.8-1.8,11.1c-1.2,3.3-3,6.1-5.5,8.3c-2.5,2.3-5.7,4-9.6,5.2c-3.9,1.2-8.5,1.8-13.9,1.8
        c-5.4,0-10.1-0.5-14.4-1.5c-4.2-1-7.8-2.6-10.7-4.9c-2.9-2.2-5.1-5.1-6.7-8.7c-1.5-3.5-2.3-7.8-2.3-12.9v-47c0-1.6-0.2-2.9-0.5-3.9
        c-0.3-1-0.8-1.7-1.4-2.2c-0.6-0.5-1.2-0.9-2-1c-0.7-0.2-1.5-0.3-2.4-0.3h-5.3v-6.5h42.4v6.5H56c-0.8,0-1.6,0.1-2.4,0.3
        c-0.8,0.2-1.4,0.6-2,1.1c-0.6,0.6-1,1.4-1.3,2.4c-0.3,1-0.5,2.4-0.5,4.2v47.5c0,3.4,0.4,6.3,1.3,8.7c0.9,2.3,2.1,4.2,3.7,5.6
        c1.6,1.4,3.5,2.4,5.8,3.1c2.2,0.6,4.8,0.9,7.6,0.9c2.7,0,5.2-0.3,7.4-1c2.2-0.7,4.1-1.8,5.6-3.3c1.5-1.5,2.7-3.4,3.6-5.6
        c0.8-2.3,1.3-5,1.3-8.1V26.3c0-1.6-0.2-2.9-0.5-3.9c-0.3-1-0.8-1.7-1.3-2.2c-0.6-0.5-1.3-0.9-2-1c-0.8-0.2-1.6-0.3-2.4-0.3h-5.2
        v-6.5h32.2V18.8z"/>
        <path  d="M16.8,112.3v-6.1h91.8v6.1H16.8z"/>
            </g>
            </svg>""")
])

