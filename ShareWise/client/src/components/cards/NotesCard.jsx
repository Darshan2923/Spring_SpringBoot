import React from 'react';
import PropTypes from 'prop-types';
import './BookCard.css';
import Rating from './Rating'; // Assuming you have a Rating component

const BookCard = ({ book, manage, onShare, onArchive, onAddToWaitingList, onBorrow, onEdit, onShowDetails }) => {
    const bookCover = book.cover
        ? `data:image/jpg;base64,${book.cover}`
        : 'https://source.unsplash.com/user/c_v_r/1900x800';

    return (
        <div className={`card ${book.shareable ? 'border-success' : ''} ${book.archived ? 'border-warning' : ''}`} style={{ width: '20rem' }}>
            <img height="200" src={bookCover} className="card-img-top" alt="Book Cover" />
            <div className="card-body overflow-scroll">
                <h5 className="card-title fs-6 text-nowrap fw-bold mb-1">
                    <i className="fas fa-book"></i>&nbsp;{book.title}
                </h5>
                <h5 className="card-subtitle fs-6 text-secondary mb-1">
                    <i className="fas fa-user-check"></i>&nbsp;{book.authorName}
                </h5>
                <h6 className="card-subtitle fs-6 text-secondary mb-1">
                    <i className="fas fa-code"></i>&nbsp;{book.isbn}
                </h6>
                <h6 className="card-subtitle fs-6 text-secondary">
                    <i className="fas fa-user"></i>&nbsp;{book.owner}
                </h6>
                <hr />
                <p className="card-text">{book.synopsis}</p>
            </div>
            <div className="card-footer d-flex gap-2 justify-content-between align-items-center">
                <div className="d-flex gap-2">
                    <Rating rating={book.rate || 0} />
                    {book.rate > 0 && <span className="fw-bold">{book.rate}</span>}
                </div>
                {!manage ? (
                    <div className="d-flex gap-2">
                        <i onClick={onShowDetails} className="fas fa-circle-info text-primary"></i>
                        <i onClick={onBorrow} className="fas fa-list-check text-primary"></i>
                        <i onClick={onAddToWaitingList} className="fas fa-heart text-danger"></i>
                    </div>
                ) : (
                    <div className="d-flex gap-2">
                        <i onClick={onEdit} className="fas fa-edit text-success"></i>
                        <i onClick={onShare} className="fas fa-share-nodes text-primary"></i>
                        <i onClick={onArchive} className="fas fa-archive text-danger"></i>
                    </div>
                )}
            </div>
        </div>
    );
};

BookCard.propTypes = {
    book: PropTypes.object.isRequired,
    manage: PropTypes.bool,
    onShare: PropTypes.func,
    onArchive: PropTypes.func,
    onAddToWaitingList: PropTypes.func,
    onBorrow: PropTypes.func,
    onEdit: PropTypes.func,
    onShowDetails: PropTypes.func
};

export default BookCard;
