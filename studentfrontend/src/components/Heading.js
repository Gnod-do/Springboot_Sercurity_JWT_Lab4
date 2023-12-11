import React from "react";

export default function Heading() {
    const styles = {
        h2: {
            fontFamily: "monospace",
            fontWeight: "bolder",
            fontSize: "250%",
            color: "rgb(112 88 143)",
            backgroundColor: "rgb(19 22 15)"
        }
    }
    return (
        <h2 style={styles.h2} align="center" id="authors">Do Van Dong, var. 861406, P3225</h2>
    )
}