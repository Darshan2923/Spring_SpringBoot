import React from 'react';
import "../styles/Emulator.css";

const Emulator = (props) => {

    const { html, css, js } = props.values;

    return (
        <div className='emulator'>
            <iframe
                srcDoc={`<html>
                <body>${html || ""}</body>
                <style>${css || ""}</style>
                <script>${js || ""}</script>
                </html>`}
            />
        </div>
    )
}

export default Emulator