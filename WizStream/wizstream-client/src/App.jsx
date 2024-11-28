import React, { useState } from 'react'
import VideoUpload from './components/VideoUpload'
import { Toaster } from 'react-hot-toast'
import VideoPlayer from './components/VideoPlayer'
import { Button, TextInput } from 'flowbite-react'

const App = () => {

  const [videoId, setVideoId] = useState('<some_video_id>');
  const [fieldValue, setFieldValue] = useState(null);

  return (
    <>
      <Toaster />
      <div className='flex flex-col items-center space-y-5 justify-center'>
        <h1 className="p-9 text-3xl font-bold text-gray-700 dark:text-gray-100">
          WizStream - Your own video streaming app
        </h1>

        <div className='flex mt-14 w-full space-x-2 justify-between'>
          <div className="w-full">
            <h1 className='text-white'>Playing video</h1>
            {/* <video style={{ width: 500, height: 500 }} src={`http://localhost:8080/api/v1/videos/stream/${videoId}/`} controls></video>
         */}

            {/* using only videojs */}
            {/* <video
            id="my-video"
            class="video-js"
            controls
            preload="auto"
            width="640"
            data-setup="{}"
          >
            {/* <source src={`http:localhost:8080/api/v1/videos/stream/range/${videoId}`} type="video/mp4" />
            <source src={`http:localhost:8080/api/v1/videos/${videoId}/master.m3u8`} type="video/mp4" />

            <p class="vjs-no-js">
              To view this video please enable JavaScript, and consider upgrading to a
              web browser that
              <a href="https://videojs.com/html5-video-support/" target="_blank"
              >supports HTML5 video</a
              >
            </p>
          </video> */}

            <div>
              <VideoPlayer src={`http:localhost:8080/api/v1/videos/${videoId}/master.m3u8`} />
            </div>

          </div>
        </div>
        <VideoUpload />
      </div>
      <div className='my-4 justify-center flex space-x-4'>
        <TextInput onClick={(event) => {
          setFieldValue(event.target.value)
        }} placeholder='Enter video id here...' name='video_id_field' />
        <Button onClick={() => {
          setVideoId(fieldValue);
        }}>Play</Button>
      </div>

    </>
  )
}

export default App