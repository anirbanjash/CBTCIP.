<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Guess the Number Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        .video-container {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -1; /* Place the video behind other content */
        }
        video {
            object-fit: cover;
            width: 100%;
            height: 100%;
        }
        .content {
            position: relative;
            z-index: 1; /* Place the content on top of the video */
        }
        h1 {
            color: Yellow;
            font-size: 80px;
            animation: textAnimation 2s ease-in-out;
        }
        @keyframes textAnimation {
            0% {
                transform: translateY(-50px);
                opacity: 0;
            }
            100% {
                transform: translateY(0);
                opacity: 1;
            }
        }
    </style>
</head>
<body>
    <div class="video-container">
        <video autoplay loop muted>
            <source src="https://dsqqu7oxq6o1v.cloudfront.net/1736178-LP6VvW99d4-high.mp4" type="video/mp4">
        </video>
    </div>
    <div class="content">
        <h1>${res}</h1>
    </div>
</body>
</html>
