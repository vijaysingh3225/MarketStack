import { useState, ChangeEvent, MouseEvent } from "react";
import Papa from "papaparse";
import '../StyleSheets/Import.css';

interface CsvData {
    [key: string]: any;
}

interface TradeExec {
    account: string;
    tradeDate: string;
    settlementDate: string;
    currency: string;
    type: number;
    side: string;
    symbol: string;
    quantity: number;
    price: number;
    execTime: string;
    commission: number;
    secFee: number;
    taf: number;
    nscc: number;
    nasdaq: number;
    ecnRemove: number;
    ecnAdd: number;
    grossProceeds: number;
    netProceeds: number;
    clearingBroker: string;
    liquidity: string;
    note: string;
}



function ImportButton() {
    const [selectedFile, setSelectedFile] = useState<File | null>(null);
    const [jsonData, setJsonData] = useState<TradeExec[]>([]);

    const handleFileChange = (event: ChangeEvent<HTMLInputElement>) => {
        const file = event.target.files ? event.target.files[0] : null;
        setSelectedFile(file);
    };

    const handleImport = (event: MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        if (!selectedFile) {
            alert("Please select a file first.");
            return;
        }

        const reader = new FileReader();
        reader.onload = () => {
            const csvData = reader.result as string;

            Papa.parse(csvData, {
                header: true,
                skipEmptyLines: true,
                complete: (results) => {
                    const parsedData = results.data as CsvData[];

                    const transformedData: TradeExec[] = parsedData.map(row => ({
                        account: row["Account"] || "",
                        tradeDate: row["T/D"] ? new Date(row["T/D"]).toISOString().split('T')[0] : "",
                        settlementDate: row["S/D"] ? new Date(row["S/D"]).toISOString().split('T')[0] : "",
                        currency: row["Currency"] || "",
                        type: isNaN(Number(row["Type"])) ? 0 : Number(row["Type"]),
                        side: row["Side"] || "",
                        symbol: row["Symbol"] || "",
                        quantity: isNaN(Number(row["Qty"])) ? 0 : Number(row["Qty"]),
                        price: isNaN(parseFloat(row["Price"])) ? 0.0 : parseFloat(row["Price"]),
                        execTime: row["Exec Time"] || "",
                        commission: isNaN(parseFloat(row["Comm"])) ? 0.0 : parseFloat(row["Comm"]),
                        secFee: isNaN(parseFloat(row["SEC"])) ? 0.0 : parseFloat(row["SEC"]),
                        taf: isNaN(parseFloat(row["TAF"])) ? 0.0 : parseFloat(row["TAF"]),
                        nscc: isNaN(parseFloat(row["NSCC"])) ? 0.0 : parseFloat(row["NSCC"]),
                        nasdaq: isNaN(parseFloat(row["Nasdaq"])) ? 0.0 : parseFloat(row["Nasdaq"]),
                        ecnRemove: isNaN(parseFloat(row["ECN Remove"])) ? 0.0 : parseFloat(row["ECN Remove"]),
                        ecnAdd: isNaN(parseFloat(row["ECN Add"])) ? 0.0 : parseFloat(row["ECN Add"]),
                        grossProceeds: isNaN(parseFloat(row["Gross Proceeds"])) ? 0.0 : parseFloat(row["Gross Proceeds"]),
                        netProceeds: isNaN(parseFloat(row["Net Proceeds"])) ? 0.0 : parseFloat(row["Net Proceeds"]),
                        clearingBroker: row["Clr Broker"] || "",
                        liquidity: row["Liq"] || "",
                        note: row["Note"] || "",
                    }));

                    setJsonData(transformedData);

                    fetch("https://backend.marketstack.quest/api/v1/closedTrades", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(transformedData),
                    })
                        .then(response => {
                            if (response.ok) {
                                alert("Data successfully posted!");
                            } else {
                                alert("Failed to post data");
                            }
                        })
                        .catch(error => {
                            console.error("Error posting data:", error);
                            alert("Error posting data");
                        });
                },
                error: (error) => {
                    console.error("Error parsing CSV:", error);
                },
            });
        };

        reader.readAsText(selectedFile);
    };

    return (
        <div className="file-import-container">
            <input type="file" accept=".csv" id="file" className="hidden" onChange={handleFileChange} />
            <label htmlFor="file" className="button">Choose File</label>
            <button type="button" onClick={handleImport} className="button">Import</button>
        </div>
    );
}

export default ImportButton;