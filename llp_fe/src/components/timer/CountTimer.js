import PropTypes from "prop-types";
import CountDownTimer from "./CountDownTimer";
import CountUpTimer from "./CountUpTimer";

CountTimer.propTypes = {
	timeLimit: PropTypes.number,
	formatClock: PropTypes.string,
	storageName: PropTypes.string,
	completedFunction: PropTypes.func,
};

export default function CountTimer({
	timeLimit,
	formatClock,
	storageName,
	completedFunction,
}) {
	return !!timeLimit ? (
		<CountDownTimer
			deadline={parseInt(timeLimit) * 60 * 1000}
			completedFunction={completedFunction}
			formatClock={formatClock || "hh:mm:ss"}
			storageName={storageName + "_D"}
		/>
	) : (
		<CountUpTimer
			isStarted={true}
			formatClock={formatClock || "hh:mm:ss"}
			storageName={storageName + "_U"}
		/>
	);
}
