import React from "react";
import { Form, Button, Container, Row, Col } from "react-bootstrap";
import { useSelector, useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import {
  clearEmailAction,
  clearPasswordAction,
  setEmailAction,
  setPasswordAction,
} from "../../Services/actions/EmailAction";

const Login = () => {
  const navigate = useNavigate();

  const email = useSelector((state) => state.loginRed.email);
  const password = useSelector((state) => state.loginRed.password);
  const dispatch = useDispatch();

  const handleLogin = (e) => {
    e.preventDefault();
    // Hardcoded email and password
    const hardcodedEmail = "admin";
    const hardcodedPassword = "admin";
    if (email === hardcodedEmail && password === hardcodedPassword) {
      // Login successful, navigate to "/test"

      navigate("/test");
      // Clear email and password state
      dispatch(clearEmailAction());
      dispatch(clearPasswordAction());
    } else {
      console.log("Login failed"); // You can add a proper error message here
    }

    // Add your login logic here
    console.log("Logging in with:", email, password);
  };

  return (
    <Container>
      <Row className="justify-content-center mt-5">
        <Col xs={12} md={6}>
          <h1>Login Page</h1>
          <Form>
            <Form.Group controlId="email">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                type="email"
                placeholder="Enter email"
                value={email}
                onChange={(e) => dispatch(setEmailAction(e.target.value))}
              />
            </Form.Group>

            <Form.Group controlId="password">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => dispatch(setPasswordAction(e.target.value))}
              />
            </Form.Group>

            <Button
              className="mt-1"
              variant="primary"
              type="submit"
              onClick={handleLogin}
            >
              Login
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default Login;
