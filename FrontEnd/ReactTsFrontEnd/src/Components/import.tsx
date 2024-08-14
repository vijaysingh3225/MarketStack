import { useState } from "react";

function ImportButton(){

    const inputHandler = new FileReader;
    const [selectedFile, setSelectedFile] = useState(null);


    return(
        <div>
            <input type="file"/>
            <button type="submit">Import</button>
        </div>
    )
}

export default ImportButton