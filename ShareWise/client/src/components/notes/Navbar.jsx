import React, { useEffect, useState } from 'react';
import { useNavigate, Link, useLocation } from 'react-router-dom';
import './Navbar.css'; // For the SCSS styling

const Navbar = () => {
    const [activeLink, setActiveLink] = useState('');
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        // Set the active link based on the current URL
        const currentPath = location.pathname;
        setActiveLink(currentPath);
    }, [location]);

    const handleLogout = () => {
        // Logout logic will go here later
        console.log('Logging out...');
    };

    return (
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
                <a className="navbar-brand" href="javascript:void(0)">BSN</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link className={`nav-link ${activeLink === '/books' ? 'active' : ''}`} to="/books">
                                <i className="fas fa-home-alt"></i>&nbsp;Home
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className={`nav-link ${activeLink === '/my-books' ? 'active' : ''}`} to="/my-books">
                                <i className="fas fa-book"></i>&nbsp;My books
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className={`nav-link ${activeLink === '/my-waiting-list' ? 'active' : ''}`} to="/my-waiting-list">
                                <i className="fas fa-heart"></i>&nbsp;My waiting list
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className={`nav-link ${activeLink === '/my-returned-books' ? 'active' : ''}`} to="/my-returned-books">
                                <i className="fa-solid fa-truck-fast"></i>&nbsp;My returned books
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className={`nav-link ${activeLink === '/my-borrowed-books' ? 'active' : ''}`} to="/my-borrowed-books">
                                <i className="fas fa-list-check"></i>&nbsp;Borrowed books
                            </Link>
                        </li>
                    </ul>
                    <form className="d-flex gap-2 align-items-center" role="search">
                        <input className="form-control" type="search" placeholder="Find a book" aria-label="Search" />
                        <button className="btn btn-outline-success" type="submit"><i className="fas fa-search"></i></button>
                        <span>Welcome</span>
                        <span className="text-capitalize fw-bold">alibou</span>
                        <button className="btn btn-link" type="button" onClick={handleLogout}>
                            <i className="fas fa-door-open"></i>
                        </button>
                    </form>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
