import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import WelcomePage from './components/pages/WelcomePage';
import Community from './components/pages/Community';
import Account from './components/pages/Account';
import Recommendations from './components/pages/Recommendations';
import BookBot from './components/pages/BookBot';
import Navbar from './components/Navbar';
import AddBook from './components/pages/AddBook';

function App() {
  return (
    <Router>
      <Routes>
        {/* Home Page */}
        <Route path="/" element={<WelcomePage />} />
        
        {/* Other Pages */}
        <Route path="/Community" element={<Community />} />
        <Route path="/Recommendations" element={<Recommendations />} />
        <Route path="/Account" element={<Account />} />
        <Route path="/Recommendations/BookBot" element={<BookBot />} />
        <Route path="/Account/AddBook" element={<AddBook/>} />


        

        
        {/* Catch-all route for unmatched paths */}
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>

      {/* Navbar outside of Routes */}
      {window.location.pathname !== '/' && <Navbar />}
    </Router>
  );
}

export default App;
