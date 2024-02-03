export const isBlockTest = (blockTestArray, test_id) => {
    let exstBlockId = blockTestArray?.find(item => item.id == test_id)

    if (exstBlockId && exstBlockId.numWarning >= 2) {
        return true
    }

    return false
}

export const messageWarningBlock = (blockTestArray, test_id) => {
    let exstBlockId = blockTestArray?.find(item => item.id == test_id)

    if (exstBlockId && exstBlockId.numWarning === 1) {
        alert("You are taking a test. Don't go out or you will be locked!")
    }

    if (exstBlockId && exstBlockId.numWarning === 2) {
        alert("This test has been blocked!")
    }
}
