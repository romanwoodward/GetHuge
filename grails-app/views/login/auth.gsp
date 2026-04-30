<!doctype html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <title>Sign in</title>
    <style>
        .auth-shell {
            min-height: calc(100vh - 120px);
            display: grid;
            place-items: center;
            padding: 2rem 1rem;
            background:
                radial-gradient(circle at top left, rgba(13, 110, 253, 0.10), transparent 32%),
                radial-gradient(circle at bottom right, rgba(25, 135, 84, 0.10), transparent 28%),
                linear-gradient(180deg, #f8fafc 0%, #ffffff 100%);
        }
        .auth-card {
            width: min(920px, 100%);
            border: 1px solid rgba(15, 23, 42, 0.08);
            box-shadow: 0 18px 50px rgba(15, 23, 42, 0.10);
            overflow: hidden;
            border-radius: 1.25rem;
            background: #fff;
        }
        .auth-panel {
            padding: 2rem;
        }
        .auth-hero {
            background: linear-gradient(135deg, #0f172a 0%, #1d4ed8 55%, #0ea5e9 100%);
            color: #fff;
            padding: 2rem;
        }
        .google-btn {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            gap: .65rem;
            width: 100%;
            border-radius: .85rem;
            padding: .85rem 1rem;
            font-weight: 600;
            text-decoration: none;
            color: #1f2937;
            background: #fff;
            border: 1px solid rgba(31, 41, 55, 0.18);
            transition: transform .15s ease, box-shadow .15s ease, border-color .15s ease;
        }
        .google-btn:hover {
            transform: translateY(-1px);
            box-shadow: 0 12px 24px rgba(15, 23, 42, 0.10);
            border-color: rgba(31, 41, 55, 0.30);
            text-decoration: none;
        }
        .google-mark {
            width: 1.1rem;
            height: 1.1rem;
            border-radius: 999px;
            background:
                radial-gradient(circle at 30% 30%, #fff 0 18%, transparent 19%),
                conic-gradient(#ea4335 0 25%, #fbbc05 25% 50%, #34a853 50% 75%, #4285f4 75% 100%);
            box-shadow: inset 0 0 0 2px rgba(255,255,255,.75);
        }
    </style>
</head>
<body>
<section class="auth-shell">
    <div class="auth-card row g-0">
        <div class="col-12 col-lg-5 auth-hero d-flex flex-column justify-content-between">
            <div>
                <div class="text-uppercase small fw-semibold opacity-75 mb-3" style="letter-spacing: .12em;">
                    GetHuge
                </div>
                <h1 class="display-6 fw-bold mb-3">Sign in and keep your workouts moving.</h1>
                <p class="lead mb-0 opacity-75">
                    Use your local account, or jump in with Google and let us create your account for you.
                </p>
            </div>
            <div class="small opacity-75 mt-4">
                Google sign-in will create and store the local user record in the database on first login.
            </div>
        </div>
        <div class="col-12 col-lg-7 auth-panel">
            <g:if test="${flash.message}">
                <div class="alert alert-info">${flash.message}</div>
            </g:if>
            <g:if test="${params.error}">
                <div class="alert alert-danger">
                    Sign-in failed. Please try again.
                </div>
            </g:if>

            <h2 class="h4 fw-bold mb-2">Welcome back</h2>
            <p class="text-body-secondary mb-4">Choose your sign-in method.</p>

            <div class="mb-4">
                <oauth2:connect provider="google" id="google-connect-link" class="google-btn">
                    <span class="google-mark" aria-hidden="true"></span>
                    Continue with Google
                </oauth2:connect>
            </div>

            <div class="d-flex align-items-center gap-3 my-4">
                <hr class="flex-grow-1"/>
                <span class="text-body-secondary small">or use your password</span>
                <hr class="flex-grow-1"/>
            </div>

            <form action="${createLink(uri: '/login/authenticate')}" method="POST" autocomplete="off">
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control form-control-lg" name="username" id="username" autofocus="autofocus"/>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control form-control-lg" name="password" id="password"/>
                </div>
                <div class="form-check mb-4">
                    <input class="form-check-input" type="checkbox" name="remember-me" id="remember-me"/>
                    <label class="form-check-label" for="remember-me">Remember me</label>
                </div>
                <div class="d-grid">
                    <button type="submit" class="btn btn-primary btn-lg">Sign in</button>
                </div>
            </form>
        </div>
    </div>
</section>
</body>
</html>
