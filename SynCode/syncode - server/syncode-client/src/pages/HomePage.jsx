import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { v4 as uuidv4 } from "uuid";
import "../styles/HomePage.css"


const HomePage = () => {

    const navigate = useNavigate();

    const [projectID, serProjectID] = useState("");

    const createNewProject = () => {
        navigate("/project/" + uuidv4());
    };

    const openProject = (e) => {
        if (projectID === "") return;
        navigate("/project/" + clearUrl(projectID));
    };

    const clearUrl = (value) => {
        if (value.includes("project/")) {
            return value.split("project/")[1];
        }
        return value;
    };

    return (
        <div className='home_root'>
            <div className="home">
                <span className="home_title">SynCode - Collaborative HTML-CSS-JS Editor</span>
                <br />
                <Button text={"Create New"} onClick={createNewProject} />
                <h3>OR</h3>
                <div className="home_open_project">
                    <InputComponent
                        onChange={(e) => serProjectID(e.target.value)}
                        value={projectID}
                        placeholder="project id or url"
                    />
                    <Button text={"Open Project"} onClick={openProject} />
                </div>
            </div>
        </div>
    )
}

export default HomePage