import React, { useState } from 'react'
import VideoUpload from './components/VideoUpload'
import { Toaster } from 'react-hot-toast'

const App = () => {

  const [videoId, setVideoId] = useState('<some_video_id>')

  return (
    <>
      <Toaster />
      <div className='flex flex-col items-center space-y-5 justify-center'>
        <h1 className="p-9 text-3xl font-bold text-gray-700 dark:text-gray-100">
          WizStream - Your own video streaming app
        </h1>

        <div>
          <h1 className='text-white'>Playing video</h1>
          {/* <video style={{ width: 500, height: 500 }} src={`http://localhost:8080/api/v1/videos/stream/${videoId}/`} controls></video>
         */}
          <video
            id="my-video"
            class="video-js"
            controls
            preload="auto"
            width="640"
            data-setup="{}"
          >
            <source src={`http:localhost:8080/api/v1/videos/stream/range/${videoId}`} type="video/mp4" />

            <p class="vjs-no-js">
              To view this video please enable JavaScript, and consider upgrading to a
              web browser that
              <a href="https://videojs.com/html5-video-support/" target="_blank"
              >supports HTML5 video</a
              >
            </p>
          </video>

        </div>
        <VideoUpload />
      </div>
    </>
  )
}

export default App