import React from 'react'
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomePage from './pages/HomePage';
import PlaygroundPage from './pages/PlaygroundPage';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<HomePage />} />
        <Route path='project/:projectID' element={<PlaygroundPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App