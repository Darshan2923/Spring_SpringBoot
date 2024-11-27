import React, { useState } from 'react'
import videoLogo from '../assets/upload.png'
import { Alert, Button, Card, Label, Progress, Textarea, TextInput } from 'flowbite-react'
import axios from 'axios';
import toast from 'react-hot-toast';

const VideoUpload = () => {

    const [selectedFile, setSelectedFile] = useState(null);
    const [progress, setProgress] = useState(0);
    const [meta, setMeta] = useState({
        title: "",
        description: "",
    })
    const [uploading, setUploading] = useState(false);
    const [message, setMessage] = useState("");

    function handleFileChange(event) {
        console.log(event.target.files[0]);
        setSelectedFile(event.target.files[0]);
    }

    function formFieldChange(event) {
        setMeta({
            ...meta,
            [event.target.name]: event.target.value
        })
    }

    function handleForm(formEvent) {
        formEvent.preventDefault();
        if (!selectedFile) {
            alert("Select a file to upload!!!");
            return;
        }
        saveVideoToServer(selectedFile, meta);

    }

    function resetForm() {
        setMeta({
            title: "",
            description: "",
        });
        setUploading(false);
        selectedFile(null);
    }

    async function saveVideoToServer(video, videoMetaData) {
        setUploading(true);
        // api call
        try {

            let formData = new FormData();
            formData.append("title", videoMetaData.title);
            formData.append("description", videoMetaData.description);
            formData.append("file", selectedFile);
            const response = await axios.post("http://localhost:8080/api/v1/videos", formData, {
                headers: { "Content-Type": "multipart/form-data" },
                onUploadProgress: (progressEvent) => {
                    const progress = Math.round((progressEvent.loaded * 100) / progressEvent.total);
                    console.log(progress);
                    setProgress(progress);
                }
            })
            setMessage("File uploaded");
            setUploading(false);
            toast.success("File uploaded!!!")
            resetForm();
            console.log(response);
            setProgress(0);


        } catch (error) {
            console.log('====================================');
            console.log(error);
            console.log('====================================');
            setMessage("Error in uploading file");
            toast.error("An error occured!!!")
            setUploading(false);
        }


    }


    return (
        <div className='text-white'>
            <Card>
                <h1 className='font-bold'>Upload File</h1>

                <div> <form noValidate onSubmit={handleForm} className="flex flex-col items-center space-x-6 space-y-6">
                    <div>
                        <div>
                            <Label htmlFor="file-input-title-text" value="Video Input" />
                        </div>
                        <TextInput value={meta.title} onChange={formFieldChange} placeholder='Enter title here...' className='w-[325px] pt-2' name='title' />
                    </div>

                    <div className="max-w-md">
                        <div className="mb-2 block">
                            <Label htmlFor="comment" value="Video decsription" />
                        </div>
                        <Textarea value={meta.description} onChange={formFieldChange} name='description' id="comment" placeholder="Video description..." required rows={4} className='w-[350px] pt-2' />
                    </div>

                    <div className='flex items-center space-x-5 justify-center'>
                        <div className="shrink-0">
                            <img className="h-16 w-16 object-cover" src={videoLogo} alt="Current profile photo" />
                        </div>
                        <label className="block">
                            <span className="sr-only">Choose profile photo</span>
                            <input name='file' type="file" onChange={handleFileChange} className="block w-full text-sm text-slate-500
      file:mr-4 file:py-2 file:px-4
      file:rounded-full file:border-0
      file:text-sm file:font-semibold
      file:bg-violet-50 file:text-violet-700
      hover:file:bg-violet-100
    "/>
                        </label>
                    </div>
                    <div className="flex justify-center">
                        {uploading && <Progress progress={progress} textLabel='Uploading...' size='lg' labelProgress labelText className='w-[350px]' />}
                    </div>
                    <div>
                        <Button disabled={uploading} type='submit'>Upload</Button>
                    </div>
                </form></div>


            </Card>
        </div>
    )
}

export default VideoUpload