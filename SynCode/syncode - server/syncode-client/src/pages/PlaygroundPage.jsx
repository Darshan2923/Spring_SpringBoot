import React from 'react'
import SplitPane from "react-split-pane";
import Pane from "react-split-pane/lib/Pane";
import CodeEditor from '../components/CodeEditor';

const PlaygroundPage = () => {
    return (
        <div className='playground'>
            <SplitPane split='vertical'>
                <Pane initialSize="34%" minSize="15%">
                    <CodeEditor
                        name="HTML"
                        lang="xml"
                        handleChange={onHtmlChange}
                    />
                </Pane>
                <Pane initialSize="33%" minSize="15%">
                    <CodeEditor
                        name="CSS"
                        lang="css"
                        value={css}
                        handleChange={onCssChange}
                    />
                </Pane>
                <Pane initialSize="33%" minSize="15%">
                    <CodeEditor
                        name="JS"
                        lang="javascript"
                        value={js}
                        handleChange={onJsChange}
                    />
                </Pane>
            </SplitPane>

            <Emulator values={delayedCodes} />

        </div>
    )
}

export default PlaygroundPage