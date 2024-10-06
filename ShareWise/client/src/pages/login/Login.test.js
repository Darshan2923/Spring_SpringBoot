import { render, fireEvent, screen } from '@testing-library/react';
import Login from './Login';
import { MemoryRouter } from 'react-router-dom';

test('should render login component', () => {
    render(
        <MemoryRouter>
            <Login />
        </MemoryRouter>
    );
    const loginButton = screen.getByText(/sign in/i);
    expect(loginButton).toBeInTheDocument();
});

test('should display error message on failed login', async () => {
    render(
        <MemoryRouter>
            <Login />
        </MemoryRouter>
    );

    fireEvent.click(screen.getByText(/sign in/i));
    const errorMessage = await screen.findByText(/invalid credentials/i);
    expect(errorMessage).toBeInTheDocument();
});
