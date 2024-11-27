import React from 'react'
import VideoUpload from './components/VideoUpload'
import { Toaster } from 'react-hot-toast'

const App = () => {
  return (
    <>
      <Toaster />
      <div className='flex flex-col items-center space-y-5 justify-center'>
        <h1 className="p-9 text-3xl font-bold text-gray-700 dark:text-gray-100">
          WizStream - Your own video streaming app
        </h1>
        <VideoUpload />
      </div>
    </>
  )
}

export default App