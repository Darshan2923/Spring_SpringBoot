import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Login from './pages/login/Login'
import Register from './pages/register/Register'
import ActivateAccount from './pages/activate-account/ActivateAccount'
import Notes from './pages/main/Notes'

const App = () => {
  return (

    <Routes>
      <Route path="/notes" element={<Notes />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/activate-account" element={<ActivateAccount />} />
    </Routes>

  )
}

export default App