import React, { useState } from "react";
import { Form, Button, Container, Row, Col } from "react-bootstrap";
import { useSelector, useDispatch } from "react-redux";
import {
  setEmailAction,
  setPasswordAction,
} from "../../Services/actions/EmailAction";

const Login = () => {
  //
  const email = useSelector((state) => state.loginRed.email);
  const password = useSelector((state) => state.loginRed.password);
  const dispatch = useDispatch();

  const handleLogin = (e) => {
    e.preventDefault();
    // Add your login logic here
    console.log("Logging in with:", email, password);
  };

  return (
    <Container>
      <Row className="justify-content-center mt-5">
        <h1>Login Page</h1>
        <Col xs={12} md={6}>
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
