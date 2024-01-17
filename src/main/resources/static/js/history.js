// Sample data (replace with your own data)
const interviews = [
  {
    id: 1,
    date: "2023-06-22",
    question: "Tell me about yourself",
    position: "Software Engineer",
    score: 8.5,
    feedback: "Great job!",
  },
  {
    id: 2,
    date: "2023-06-23",
    question: "What is your greatest weakness?",
    position: "Product Manager",
    score: 7.2,
    feedback: "Some improvement needed.",
  },
  {
    id: 3,
    date: "2023-06-24",
    question: "Why do you want to work here?",
    position: "Software Engineer",
    score: 9.0,
    feedback: "Excellent performance!",
  },
  {
    id: 4,
    date: "2023-06-25",
    question: "Describe a challenging situation you faced at work.",
    position: "Product Manager",
    score: 8.7,
    feedback: "Impressive!",
  },
];

// DOM elements
const interviewTable = document.getElementById("interviews");
const searchInput = document.getElementById("search-input");
const searchButton = document.getElementById("search-button");
const questionFilter = document.getElementById("question-filter");
const positionFilter = document.getElementById("position-filter");

// Initialize interview table
populateInterviewTable();

// Populate interview table
function populateInterviewTable() {
  const tbody = interviewTable.querySelector("tbody");
  tbody.innerHTML = "";

  interviews.forEach((interview) => {
    const row = document.createElement("tr");
    row.innerHTML = `
        <td>${interview.id}</td>
        <td>${interview.date}</td>
        <td>${interview.question}</td>
        <td>${interview.position}</td>
        <td>${interview.score}</td>
      `;
    row.addEventListener("click", () => showInterviewDetails(interview));
    tbody.appendChild(row);
  });
}

// Populate interview question filter
function populateQuestionFilter() {
  const questions = interviews.map((interview) => interview.question);
  const uniqueQuestions = [...new Set(questions)];

  questionFilter.innerHTML = '<option value="">All</option>';
  uniqueQuestions.forEach((question) => {
    const option = document.createElement("option");
    option.value = question;
    option.textContent = question;
    questionFilter.appendChild(option);
  });
}

// Populate interview position filter
function populatePositionFilter() {
  const positions = interviews.map((interview) => interview.position);
  const uniquePositions = [...new Set(positions)];

  positionFilter.innerHTML = '<option value="">All</option>';
  uniquePositions.forEach((position) => {
    const option = document.createElement("option");
    option.value = position;
    option.textContent = position;
    positionFilter.appendChild(option);
  });
}

// Show interview details
function showInterviewDetails(interview) {
  const interviewDetails = document.getElementById("interview-info");

  // Create and populate details elements
  const date = document.createElement("p");
  date.textContent = `Date: ${interview.date}`;
  const question = document.createElement("p");
  question.textContent = `Question: ${interview.question}`;
  const position = document.createElement("p");
  position.textContent = `Position: ${interview.position}`;
  const score = document.createElement("p");
  score.textContent = `Score: ${interview.score}`;
  const feedback = document.createElement("p");
  feedback.textContent = `Feedback: ${interview.feedback}`;

  // Clear existing details
  interviewDetails.innerHTML = "";

  // Append details to the container
  interviewDetails.appendChild(date);
  interviewDetails.appendChild(question);
  interviewDetails.appendChild(position);
  interviewDetails.appendChild(score);
  interviewDetails.appendChild(feedback);
}

// Search interviews
searchButton.addEventListener("click", () => {
  const searchTerm = searchInput.value.toLowerCase().trim();
  const filteredInterviews = interviews.filter(
    (interview) =>
      interview.question.toLowerCase().includes(searchTerm) ||
      interview.position.toLowerCase().includes(searchTerm)
  );

  populateInterviewTable(filteredInterviews);
});

// Filter interviews by question
questionFilter.addEventListener("change", () => {
  const selectedQuestion = questionFilter.value;
  const filteredInterviews = interviews.filter(
    (interview) =>
      selectedQuestion === "" || interview.question === selectedQuestion
  );

  populateInterviewTable(filteredInterviews);
});

// Filter interviews by position
positionFilter.addEventListener("change", () => {
  const selectedPosition = positionFilter.value;
  const filteredInterviews = interviews.filter(
    (interview) =>
      selectedPosition === "" || interview.position === selectedPosition
  );

  populateInterviewTable(filteredInterviews);
});

// Sort interviews by score (highest to lowest)
function sortInterviewsByScore() {
  const sortedInterviews = [...interviews].sort((a, b) => b.score - a.score);
  populateInterviewTable(sortedInterviews);
}

// Attach event listener to the table header for sorting
const scoreHeader = interviewTable.querySelector("th:nth-child(5)");
scoreHeader.addEventListener("click", sortInterviewsByScore);
